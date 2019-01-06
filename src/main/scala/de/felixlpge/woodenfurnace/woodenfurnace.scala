package de.felixlpge.woodenfurnace

import de.felixlpge.woodenfurnace.TileEntitys.FurnaceTileEntity
import de.felixlpge.woodenfurnace.blocks.{WoodenFurnace, WoodenLever, WoodenPiston}
import de.felixlpge.woodenfurnace.config.ConfigLoader
import de.felixlpge.woodenfurnace.proxy.CommonProxy
import net.minecraft.block.Block
import net.minecraft.item.{Item, ItemBlock}
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLPostInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry

@Mod(modid = woodenfurnace.MODID, version = woodenfurnace.VERSION, modLanguage = "scala", name = "Wooden Furnace")
object woodenfurnace {
  final val MODID = "woodenfurnace"
  final val VERSION = "1.2"
  var config: ConfigLoader = _

  import net.minecraftforge.fml.common.SidedProxy

  @SidedProxy(serverSide = "de.felixlpge.woodenfurnace.proxy.CommonProxy", clientSide = "de.felixlpge.woodenfurnace.proxy.ClientProxy")
  var proxy: CommonProxy = _


  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit ={
    config = new ConfigLoader("config/woodenfurnace.json", "{\"items_smelting\": 1}")
  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit ={
    GameRegistry.registerTileEntity(classOf[FurnaceTileEntity], "tilentitywoodenfurnace")
  }



}
@Mod.EventBusSubscriber(modid = woodenfurnace.MODID)
object RegistrationHandler{
  val furnace = new WoodenFurnace
  var furnaceItem: ItemBlock = _
  val lever = new WoodenLever
  var leverItem: ItemBlock = _
  val piston = new WoodenPiston
  var pistonItem: ItemBlock = _
  @SubscribeEvent
  def registerBlocks(event: RegistryEvent.Register[Block]): Unit ={
    event.getRegistry.registerAll(furnace, lever, piston)
  }
  @SubscribeEvent
  def registerItems(event: RegistryEvent.Register[Item]): Unit = {
    furnaceItem = furnace.createItemBlock
    leverItem = lever.createItemBlock
    pistonItem = piston.createItemBlock
    event.getRegistry.registerAll(furnaceItem, leverItem, pistonItem)
    furnace.registerItemModel(furnaceItem)
    lever.registerItemModel(leverItem)
    piston.registerItemModel(pistonItem)
  }
}
