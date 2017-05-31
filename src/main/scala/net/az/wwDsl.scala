package net.az

import gov.nasa.worldwind.{Configuration, Model, WorldWind}
import gov.nasa.worldwind.avlist.AVKey

object wwDsl {
  def model:Model = WorldWind.createConfigurationComponent(AVKey.MODEL_CLASS_NAME).asInstanceOf[Model]
}
