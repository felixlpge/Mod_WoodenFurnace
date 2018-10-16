package de.felixlpge.woodenfurnace.blocks

import java.util.Random

import de.felixlpge.woodenfurnace.TileEntitys.FurnaceTileEntity
import de.felixlpge.woodenfurnace.woodenfurnace
import net.minecraft.block.BlockFurnace
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.{Item, ItemBlock}
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World

class WoodenFurnace extends BlockFurnace(false) {
  setUnlocalizedName("woodenfurnace")
  setRegistryName("woodenfurnace")
  setCreativeTab(CreativeTabs.MISC)

  override def createNewTileEntity(worldIn: _root_.net.minecraft.world.World, meta: Int): _root_.net.minecraft.tileentity.TileEntity = new FurnaceTileEntity

  def registerItemModel(itemBlock: ItemBlock): Unit = {
    woodenfurnace.proxy.registerItemRenderer(itemBlock, 0, "woodenfurnace")
  }

  import net.minecraft.item.ItemBlock

  def createItemBlock: ItemBlock = {
    var item = new ItemBlock(this)
    item.setRegistryName(getRegistryName)
    item
  }

  override def canPlaceBlockAt(worldIn: World, pos: BlockPos): Boolean = {
    if (super.canPlaceBlockAt(worldIn, pos)){
      var response = true
      if (!worldIn.isAirBlock(pos.add(1, 0, 0)))response = false
      if (!worldIn.isAirBlock(pos.add(1, 0, 1)))response = false
      if (!worldIn.isAirBlock(pos.add(0, 0, 1)))response = false
      if (!worldIn.isAirBlock(pos.add(-1, 0, 0)))response = false
      if (!worldIn.isAirBlock(pos.add(-1, 0, -1)))response = false
      if (!worldIn.isAirBlock(pos.add(0, 0, -1)))response = false
      if (!worldIn.isAirBlock(pos.add(1, 0, -1)))response = false
      if (!worldIn.isAirBlock(pos.add(-1, 0, 1)))response = false
      if (!response) worldIn.getClosestPlayer(pos.getX, pos.getY, pos.getZ, 10, false).sendStatusMessage(new TextComponentTranslation("noplace"), true)
      return response
    }
    false
  }
  override def getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item = Items.COAL


}
