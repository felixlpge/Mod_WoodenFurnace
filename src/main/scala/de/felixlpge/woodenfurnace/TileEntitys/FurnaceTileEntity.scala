package de.felixlpge.woodenfurnace.TileEntitys

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
  }




}
