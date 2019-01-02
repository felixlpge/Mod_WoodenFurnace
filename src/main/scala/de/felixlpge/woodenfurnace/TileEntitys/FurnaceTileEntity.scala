package de.felixlpge.woodenfurnace.TileEntitys

import de.felixlpge.woodenfurnace.{RegistrationHandler, woodenfurnace}
import net.minecraft.entity.item.EntityItem
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.FurnaceRecipes
import net.minecraft.tileentity.TileEntityFurnace
import net.minecraft.util.EnumParticleTypes
import net.minecraftforge.client.model.obj.OBJModel.Material


class FurnaceTileEntity extends TileEntityFurnace {

  var burned = false
  var burnedTicks = 0
  var outTicks = 0
  val maxTime: Int = Integer.parseInt(woodenfurnace.config.getConfigOption("items_smelting")) * 1500

  override def getInventoryStackLimit: Int = 1

  override def getCookTime(stack: ItemStack): Int = 1600

  override def update(): Unit = {
    var burning = this.isBurning
    super.update()
    if (!this.world.isRemote && burnedTicks >= maxTime) {
      var tile = world.getTileEntity(pos).asInstanceOf[FurnaceTileEntity]
      if (tile != null){
        var item = new EntityItem(world, pos.getX, pos.getY, pos.getZ, FurnaceRecipes.instance().getSmeltingResult(tile.getStackInSlot(0)))
        world.spawnEntity(item)
      }
      tile.setInventorySlotContents(0, new ItemStack(Blocks.AIR))
      world.setBlockToAir(pos)
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
    if (burning != this.isBurning && !burned){
      RegistrationHandler.furnace.setState(world, pos)
    }
    if (burned) {
      burnedTicks = burnedTicks + 1
    }

    if (this.isBurning) {
      burned = true
    }
  }


}
