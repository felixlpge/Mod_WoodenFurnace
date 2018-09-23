package de.felixlpge.woodenfurnace.TileEntitys

import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntityFurnace

class FurnaceTileEntity extends TileEntityFurnace{


  override def getInventoryStackLimit: Int =  1

  override def getCookTime(stack: ItemStack): Int = 100
}
