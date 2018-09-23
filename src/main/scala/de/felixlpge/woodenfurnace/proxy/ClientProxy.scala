package de.felixlpge.woodenfurnace.proxy

import de.felixlpge.woodenfurnace.woodenfurnace
import net.minecraft.item.Item

class ClientProxy extends CommonProxy {

  import net.minecraft.client.renderer.block.model.ModelResourceLocation
  import net.minecraftforge.client.model.ModelLoader

  override def registerItemRenderer(item: Item, meta: Int, id: String): Unit = {
    ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(woodenfurnace.MODID + ":" + id, "inventory"))
  }
}
