package de.felixlpge.woodenfurnace.TileEntitys

import de.felixlpge.woodenfurnace.{RegistrationHandler, woodenfurnace}
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntityFurnace
import net.minecraft.util.EnumParticleTypes


class FurnaceTileEntity extends TileEntityFurnace {

  var burned = false
  var burnedTicks = 0
  var outTicks = 0
  val maxTime: Int = Integer.parseInt(woodenfurnace.config.getConfigOption("items_smelting")) * 1610

  override def getInventoryStackLimit: Int = 1

  override def getCookTime(stack: ItemStack): Int = 1600

  override def update(): Unit = {
    var burning = this.isBurning
    super.update()
    if (!this.world.isRemote && burnedTicks >= maxTime) {
      world.destroyBlock(pos, false)
      this.world.spawnParticle(EnumParticleTypes.LAVA, pos.getX, pos.getY, pos.getZ, 1.0, 1.0, 1.0)
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
    if (burned) {
      burnedTicks = burnedTicks + 1
    }
    if (burning != this.isBurning){
      if (outTicks > 10 || !burned){
        RegistrationHandler.furnace.setState(world, pos)
        outTicks = 0
      }else{
        outTicks = outTicks + 1
      }

    }
    if (this.isBurning) {
      burned = true
    }
  }


}
