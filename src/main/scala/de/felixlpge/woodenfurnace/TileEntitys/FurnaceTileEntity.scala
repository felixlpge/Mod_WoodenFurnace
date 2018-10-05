package de.felixlpge.woodenfurnace.TileEntitys

import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntityFurnace


class FurnaceTileEntity extends TileEntityFurnace{

  var burned = false

  override def getInventoryStackLimit: Int =  1

  override def getCookTime(stack: ItemStack): Int = 1600

  override def update(): Unit = {
    super.update()
    if (this.isBurning) {
      burned = true
    }
    if (!this.world.isRemote && burned && !this.isBurning){
      world.destroyBlock(pos, false)
    }
    if (this.isBurning) {
      this.world.setBlockState(pos.add(0, 1, 0), Blocks.FIRE.getDefaultState, 11)
      this.world.setBlockState(pos.add(1, 0, 0), Blocks.FIRE.getDefaultState, 11)
      this.world.setBlockState(pos.add(0, 0, 1), Blocks.FIRE.getDefaultState, 11)
      this.world.setBlockState(pos.add(1, 0, 1), Blocks.FIRE.getDefaultState, 11)
      this.world.setBlockState(pos.add(-1, 0, 0), Blocks.FIRE.getDefaultState, 11)
      this.world.setBlockState(pos.add(0, 0, -1), Blocks.FIRE.getDefaultState, 11)
      this.world.setBlockState(pos.add(-1, 0, -1), Blocks.FIRE.getDefaultState, 11)
      this.world.setBlockState(pos.add(-1, 0, 1), Blocks.FIRE.getDefaultState, 11)
      this.world.setBlockState(pos.add(1, 0, -1), Blocks.FIRE.getDefaultState, 11)
    }
  }




}
