package com.github.notyy.typeflow.editor

import java.io.File

import com.github.notyy.typeflow.editor.CreateModel.UnsavedModel

import scala.util.{Failure, Success, Try}

object CreateNewModel {

  trait SaveNewModelResult
  case class ModelCreationSuccess(modelName: String) extends SaveNewModelResult
  case class ModelSaveFailed(modelName: String, msg: String) extends SaveNewModelResult

  def execute(model: UnsavedModel): SaveNewModelResult = {
    Try {
      val file = new File(s"./localOutput/${model.modelName}.typeflow")
      if (file.exists() && file.isFile) {
        file.delete()
      }
      file.createNewFile()
      ModelCreationSuccess(model.modelName)
    } match {
      case Success(rs) => rs
      case Failure(exception) => ModelSaveFailed(model.modelName, exception.getMessage)
    }
  }
}