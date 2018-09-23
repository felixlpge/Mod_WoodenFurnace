package de.felixlpge.woodenfurnace.blocks

import java.util.Random

import de.felixlpge.woodenfurnace.TileEntitys.FurnaceTileEntity
import de.felixlpge.woodenfurnace.woodenfurnace
import net.minecraft.block.BlockFurnace
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.item.Item

class WoodenFurnace extends BlockFurnace(false) {
  setUnlocalizedName("woodenfurnace")
  setRegistryName("woodenfurnace")
  setCreativeTab(CreativeTabs.MISC)

  override def createNewTileEntity(worldIn: _root_.net.minecraft.world.World, meta: Int): _root_.net.minecraft.tileentity.TileEntity = new FurnaceTileEntity

  def registerItemModel(itemBlock: Nothing): Unit = {
    woodenfurnace.proxy.registerItemRenderer(itemBlock, 0, "woodenfurnace")
  }

  import net.minecraft.item.ItemBlock

  def createItemBlock: Item = new ItemBlock(this).setRegistryName(getRegistryName)

  override def getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item = Item.getItemFromBlock(Blocks.LOG)


}
