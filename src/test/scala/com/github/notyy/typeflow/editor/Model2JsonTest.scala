package com.github.notyy.typeflow.editor

import com.github.notyy.typeflow.Fixtures
import org.scalatest.{FunSpec, Matchers}

class Model2JsonTest extends FunSpec with Matchers {
  val model = Fixtures.model

  describe("Model2Json"){
    it("transform Model to Json string") {
      val json = Model2Json.execute(model)
      println(json)
      json shouldBe """{"name":"typeflow_editor","definitions":[{"jsonClass":"InputEndpoint","name":"UserInputEndpoint","outputType":{"name":"UserInput"}},{"jsonClass":"Function","name":"UserInputInterpreter","inputType":{"name":"UserInput"},"outputs":[{"outputType":{"name":"UnknownCommand"},"index":1},{"outputType":{"name":"QuitCommand"},"index":2}]},{"jsonClass":"Function","name":"WrapOutput","inputType":{"name":"java.lang.Object"},"outputs":[{"outputType":{"name":"WrappedOutput"},"index":1}]},{"jsonClass":"OutputEndpoint","name":"CommandLineOutputEndpoint","inputType":{"name":"WrappedOutput"},"outputType":{"name":"Unit"},"errorOutput":[]}],"flows":[{"name":"minimalFlow","instances":[{"id":"UserInputEndpoint","definition":{"jsonClass":"InputEndpoint","name":"UserInputEndpoint","outputType":{"name":"UserInput"}}},{"id":"UserInputInterpreter","definition":{"jsonClass":"Function","name":"UserInputInterpreter","inputType":{"name":"UserInput"},"outputs":[{"outputType":{"name":"UnknownCommand"},"index":1},{"outputType":{"name":"QuitCommand"},"index":2}]}},{"id":"WrapOutput","definition":{"jsonClass":"Function","name":"WrapOutput","inputType":{"name":"java.lang.Object"},"outputs":[{"outputType":{"name":"WrappedOutput"},"index":1}]}},{"id":"CommandLineOutputEndpoint","definition":{"jsonClass":"OutputEndpoint","name":"CommandLineOutputEndpoint","inputType":{"name":"WrappedOutput"},"outputType":{"name":"Unit"},"errorOutput":[]}}],"connections":[{"fromInstanceId":"UserInputEndpoint","outputIndex":1,"toInstanceId":"UserInputInterpreter"},{"fromInstanceId":"UserInputInterpreter","outputIndex":1,"toInstanceId":"WrapOutput"},{"fromInstanceId":"UserInputInterpreter","outputIndex":2,"toInstanceId":"WrapOutput"},{"fromInstanceId":"WrapOutput","outputIndex":1,"toInstanceId":"CommandLineOutputEndpoint"}]}],"activeFlow":{"name":"minimalFlow","instances":[{"id":"UserInputEndpoint","definition":{"jsonClass":"InputEndpoint","name":"UserInputEndpoint","outputType":{"name":"UserInput"}}},{"id":"UserInputInterpreter","definition":{"jsonClass":"Function","name":"UserInputInterpreter","inputType":{"name":"UserInput"},"outputs":[{"outputType":{"name":"UnknownCommand"},"index":1},{"outputType":{"name":"QuitCommand"},"index":2}]}},{"id":"WrapOutput","definition":{"jsonClass":"Function","name":"WrapOutput","inputType":{"name":"java.lang.Object"},"outputs":[{"outputType":{"name":"WrappedOutput"},"index":1}]}},{"id":"CommandLineOutputEndpoint","definition":{"jsonClass":"OutputEndpoint","name":"CommandLineOutputEndpoint","inputType":{"name":"WrappedOutput"},"outputType":{"name":"Unit"},"errorOutput":[]}}],"connections":[{"fromInstanceId":"UserInputEndpoint","outputIndex":1,"toInstanceId":"UserInputInterpreter"},{"fromInstanceId":"UserInputInterpreter","outputIndex":1,"toInstanceId":"WrapOutput"},{"fromInstanceId":"UserInputInterpreter","outputIndex":2,"toInstanceId":"WrapOutput"},{"fromInstanceId":"WrapOutput","outputIndex":1,"toInstanceId":"CommandLineOutputEndpoint"}]}}"""
    }
  }
}