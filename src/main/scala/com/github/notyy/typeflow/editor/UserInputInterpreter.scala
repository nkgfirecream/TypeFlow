package com.github.notyy.typeflow.editor

import com.github.notyy.typeflow.domain.{Input, InputType, Output, OutputType}
import com.typesafe.scalalogging.Logger

object UserInputInterpreter {
  private val logger = Logger("UserInputInterpreter")

  private val CreateModelPattern = """create Model (.*)""".r
  private val AddInputEndpointPattern = """add InputEndpoint (.*) haveOutputType (.*) toModel (.*)""".r
  private val AddFunctionPattern = """add PureFunction (.*) haveInputs (.*) haveOutputs (.*) toModel (.*)""".r
  private val AddOutputEndpointPattern = """add OutputEndpoint (.*) haveInputs (.*) haveOutputType (.*) haveErrorOutputs (.*) toModel (.*)""".r

  private val CreateFlowPattern = """create Flow (.*) toModel (.*)""".r
  private val AddInstancePattern = """add Instance of (.*) to (.*)\.(.*)""".r
  private val ConnectElementPattern = """connect from (.*)\.(.*) to (.*)\.(.*) inModel (.*)""".r

  def execute(input: UserInput): InterpreterResult = {
    input.value match {
      case ":q" => QuitCommand
      case CreateModelPattern(modelName) => CreateModelCommand(modelName)
      case AddInputEndpointPattern(name, outputType, modelName) => AddInputEndpointCommand(modelName, name, OutputType(outputType))
      case AddFunctionPattern(name, inputs, outputs, modelName) => AddFunctionCommand(modelName, name, extractInputs(inputs), extractOutputs(outputs))
      case AddOutputEndpointPattern(name, inputs, outputType, errorOutputs, modelName) => {
        AddOutputEndpointCommand(modelName,name,extractInputs(inputs), OutputType(outputType),
          if(errorOutputs == "Empty") Vector.empty else extractOutputs(errorOutputs))
      }
      case CreateFlowPattern(name,modelName) => CreateFlowCommand(modelName, name)
      case AddInstancePattern(definitionName, modelName, flowName) => AddInstanceCommand(modelName,flowName,definitionName)
      case ConnectElementPattern(fromInstanceId,fromInputIndex, toInstanceId,toInputIndex, modelName) => ConnectElementCommand(fromInstanceId,fromInputIndex.toInt, toInstanceId,toInputIndex.toInt, modelName)
      case _ => UnknownCommand(input.value)
    }
  }

  def extractInputs(inputs: String): Vector[Input] = {
    inputs.split(";").map{ part =>
      val sect = part.split(",")
      logger.debug(s"sect=$sect")
      Input(InputType(sect.head), sect.last.toInt)
    }.toVector
  }

  def extractOutputs(outputs: String): Vector[Output] = {
    outputs.split(";").map{ part =>
      val sect = part.split(",")
      logger.debug(s"sect=$sect")
      Output(OutputType(sect.head), sect.last.toInt)
    }.toVector
  }
}
