package de.felixlpge.woodenfurnace.TileEntitys

import de.felixlpge.woodenfurnace.{RegistrationHandler, woodenfurnace}
import net.minecraft.entity.item.EntityItem
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.FurnaceRecipes
import net.minecraft.tileentity.TileEntityFurnace
import net.minecraft.util.EnumParticleTypes
import java.lang.Boolean


class FurnaceTileEntity extends TileEntityFurnace {

  var burned = false
  var burnedTicks = 0
  var burnedItemTicks = 0
  var maxBurn = 0
  var outTicks = 0
  private val maxTime: Int = Integer.parseInt(woodenfurnace.config.getConfigOption("items_smelting")) * 1500
  private val fire_activated: Boolean = Boolean.valueOf(woodenfurnace.config.getConfigOption("fire_when_smelting"))

  override def getInventoryStackLimit: Int = Integer.parseInt(woodenfurnace.config.getConfigOption("items_smelting"))

  override def getCookTime(stack: ItemStack): Int = 1600

  override def update(): Unit = {
    var burning = this.isBurning
    if (TileEntityFurnace.getItemBurnTime(getStackInSlot(1)) > 0 && getStackInSlot(0).getCount != 0) {
      maxBurn += TileEntityFurnace.getItemBurnTime(getStackInSlot(1))
    }
    super.update()
    if (TileEntityFurnace.getItemBurnTime(getStackInSlot(1)) > 0 && maxBurn > 0) {
      maxBurn -= TileEntityFurnace.getItemBurnTime(getStackInSlot(1))
    }
    if (burnedItemTicks == 1500) {
      var tile = world.getTileEntity(pos).asInstanceOf[FurnaceTileEntity]
      if (tile != null) {
        var item = new EntityItem(world, pos.getX, pos.getY, pos.getZ, FurnaceRecipes.instance().getSmeltingResult(tile.getStackInSlot(0)))
        world.spawnEntity(item)
      }
      burnedItemTicks = 0
    }
    if (!this.world.isRemote && ((maxBurn == 0 && burned) || burnedTicks > maxTime)) {
      var tile = world.getTileEntity(pos).asInstanceOf[FurnaceTileEntity]
      tile.setInventorySlotContents(0, new ItemStack(Blocks.AIR))
      world.setBlockToAir(pos)
      this.world.spawnParticle(EnumParticleTypes.LAVA, pos.getX, pos.getY, pos.getZ, 1.0, 1.0, 1.0)
    }
    if (this.isBurning && fire_activated) {
      if (this.world.getBlockState(pos).getBlock != Blocks.OBSIDIAN)
        this.world.setBlockState(pos.add(0, 1, 0), Blocks.FIRE.getDefaultState, 11)
      if (this.world.getBlockState(pos.add(1, -1, 0)).getBlock != Blocks.OBSIDIAN)
        this.world.setBlockState(pos.add(1, 0, 0), Blocks.FIRE.getDefaultState, 11)
      if (this.world.getBlockState(pos.add(0, -1, 1)).getBlock != Blocks.OBSIDIAN)
        this.world.setBlockState(pos.add(0, 0, 1), Blocks.FIRE.getDefaultState, 11)
      if (this.world.getBlockState(pos.add(1, -1, 1)).getBlock != Blocks.OBSIDIAN)
        this.world.setBlockState(pos.add(1, 0, 1), Blocks.FIRE.getDefaultState, 11)
      if (this.world.getBlockState(pos.add(-1, -1, 0)).getBlock != Blocks.OBSIDIAN)
        this.world.setBlockState(pos.add(-1, 0, 0), Blocks.FIRE.getDefaultState, 11)
      if (this.world.getBlockState(pos.add(0, -1, -1)).getBlock != Blocks.OBSIDIAN)
        this.world.setBlockState(pos.add(0, 0, -1), Blocks.FIRE.getDefaultState, 11)
      if (this.world.getBlockState(pos.add(-1, -1, -1)).getBlock != Blocks.OBSIDIAN)
        this.world.setBlockState(pos.add(-1, 0, -1), Blocks.FIRE.getDefaultState, 11)
      if (this.world.getBlockState(pos.add(-1, -1, 1)).getBlock != Blocks.OBSIDIAN)
        this.world.setBlockState(pos.add(-1, 0, 1), Blocks.FIRE.getDefaultState, 11)
      if (this.world.getBlockState(pos.add(1, -1, -1)).getBlock != Blocks.OBSIDIAN)
        this.world.setBlockState(pos.add(1, 0, -1), Blocks.FIRE.getDefaultState, 11)
    }
    if (burning != this.isBurning && !burned) {
      RegistrationHandler.furnace.setState(world, pos)
    }
    if (burned) {
      maxBurn -= 1
      burnedTicks = burnedTicks + 1
      burnedItemTicks += 1
    }

    if (this.isBurning) {
      burned = true
    }
  }


}
