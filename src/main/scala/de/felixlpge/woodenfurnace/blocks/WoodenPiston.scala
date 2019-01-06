package de.felixlpge.woodenfurnace.blocks

import de.felixlpge.woodenfurnace.woodenfurnace
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemBlock

class WoodenPiston extends Block(Material.CIRCUITS) {
  setUnlocalizedName("woodenpiston")
  setRegistryName("woodenpiston")
  setCreativeTab(CreativeTabs.REDSTONE)

  def registerItemModel(itemBlock: ItemBlock): Unit = {
    woodenfurnace.proxy.registerItemRenderer(itemBlock, 0, "woodenpiston")
  }

  import net.minecraft.item.ItemBlock

  def createItemBlock: ItemBlock = {
    var item = new ItemBlock(this)
    item.setRegistryName(getRegistryName)
    item
  }
}
