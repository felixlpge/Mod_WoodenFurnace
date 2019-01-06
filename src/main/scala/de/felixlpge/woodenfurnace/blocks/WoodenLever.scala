package de.felixlpge.woodenfurnace.blocks

import java.util.Random

import de.felixlpge.woodenfurnace.woodenfurnace
import net.minecraft.block.BlockLever
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemBlock
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.util.{EnumFacing, EnumHand}
import net.minecraft.world.World

class WoodenLever extends BlockLever {
  setUnlocalizedName("woodenfurnace:lever")
  setRegistryName("lever")
  setCreativeTab(CreativeTabs.REDSTONE)

  def registerItemModel(itemBlock: ItemBlock): Unit = {
    woodenfurnace.proxy.registerItemRenderer(itemBlock, 0, "lever")
  }

  import net.minecraft.item.ItemBlock

  def createItemBlock: ItemBlock = {
    var item = new ItemBlock(this)
    item.setRegistryName(getRegistryName)
    item
  }

  override def onBlockActivated(world : World, pos : BlockPos, blockstate : IBlockState, player : EntityPlayer, hand : EnumHand, facing : EnumFacing, p_onBlockActivated_7_ : Float, p_onBlockActivated_8_ : Float, p_onBlockActivated_9_ : Float): Boolean = {
    var bool = super.onBlockActivated(world, pos, blockstate, player, hand, facing, p_onBlockActivated_7_, p_onBlockActivated_8_, p_onBlockActivated_9_)
    var rand = new Random().nextInt(10)
    if (rand == 9){
      world.setBlockToAir(pos)
    }else{
      player.sendStatusMessage(new TextComponentTranslation("hurtWood"), true)
      player.setHealth(player.getHealth - 1)
    }
    bool
  }

}
