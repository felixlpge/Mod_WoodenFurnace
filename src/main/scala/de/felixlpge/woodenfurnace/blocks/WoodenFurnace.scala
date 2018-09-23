package de.felixlpge.woodenfurnace.blocks

import de.felixlpge.woodenfurnace.TileEntitys.FurnaceTileEntity
import net.minecraft.block.BlockFurnace
import net.minecraft.creativetab.CreativeTabs

class WoodenFurnace extends BlockFurnace(false) {
  setUnlocalizedName("woodenfurnace")
  setRegistryName("woodenfurnace")
  setCreativeTab(CreativeTabs.MISC)

  override def createNewTileEntity(worldIn: _root_.net.minecraft.world.World, meta: Int): _root_.net.minecraft.tileentity.TileEntity = new FurnaceTileEntity
}
