package de.felixlpge.woodenfurnace.blocks

import java.util.Random

import de.felixlpge.woodenfurnace.TileEntitys.FurnaceTileEntity
import de.felixlpge.woodenfurnace.{RegistrationHandler, woodenfurnace}
import net.minecraft.block.BlockFurnace
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.{Item, ItemBlock, ItemStack}
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World

class WoodenFurnace extends BlockFurnace(false) {
  setUnlocalizedName("woodenfurnace")
  setRegistryName("woodenfurnace")
  setHardness(1)
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

  def setState(worldIn: World, pos: BlockPos): Unit ={
    val iblockstate = worldIn.getBlockState(pos)
    var tileentity: FurnaceTileEntity = worldIn.getTileEntity(pos).asInstanceOf[FurnaceTileEntity]
    val item0: ItemStack = new ItemStack(tileentity.getStackInSlot(0).getItem, 1)
    val item1: ItemStack = new ItemStack(tileentity.getStackInSlot(1).getItem, 1)
    val item2: ItemStack = new ItemStack(tileentity.getStackInSlot(2).getItem, 1)
    tileentity.setInventorySlotContents(0, new ItemStack(Blocks.AIR))
    tileentity.setInventorySlotContents(1, new ItemStack(Blocks.AIR))
    tileentity.setInventorySlotContents(2, new ItemStack(Blocks.AIR))
    worldIn.setBlockState(pos, RegistrationHandler.furnace.getDefaultState.withProperty(BlockFurnace.FACING, iblockstate.getValue(BlockFurnace.FACING)), 3)
    worldIn.setBlockState(pos, RegistrationHandler.furnace.getDefaultState.withProperty(BlockFurnace.FACING, iblockstate.getValue(BlockFurnace.FACING)), 3)
    if (tileentity != null) {
      tileentity.validate()
      worldIn.setTileEntity(pos, tileentity)
      tileentity = worldIn.getTileEntity(pos).asInstanceOf[FurnaceTileEntity]
      tileentity.setInventorySlotContents(0, item0)
      tileentity.setInventorySlotContents(1, item1)
      tileentity.setInventorySlotContents(2, item2)
    }
  }

  override def breakBlock(worldIn: World, pos: BlockPos, state: IBlockState): Unit = {
    super.breakBlock(worldIn, pos, state)
    /*if (!true) {
      val tileentity = worldIn.getTileEntity(pos)
      if (tileentity.isInstanceOf[TileEntityFurnace]) {
        InventoryHelper.dropInventoryItems(worldIn, pos, tileentity.asInstanceOf[TileEntityFurnace])
        worldIn.updateComparatorOutputLevel(pos, this)
      }
      worldIn.removeTileEntity(pos)
    }*/
  }


}
