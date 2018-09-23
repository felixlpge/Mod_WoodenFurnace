package de.felixlpge.woodenfurnace

import de.felixlpge.woodenfurnace.blocks.WoodenFurnace
import net.minecraft.block.Block
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod(modid = woodenfurnace.MODID, version = woodenfurnace.VERSION, modLanguage = "scala", name = "Wooden Furnace")
object woodenfurnace {
  final val MODID = "woodenfurnace"
  final val VERSION = "1.0"
  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit ={

  }



}
@Mod.EventBusSubscriber(modid = woodenfurnace.MODID)
object RegistrationHandler{
  @SubscribeEvent
  def registerBlocks(event: RegistryEvent.Register[Block]): Unit ={
    val furnace = new WoodenFurnace
    event.getRegistry.register(furnace)
  }
}