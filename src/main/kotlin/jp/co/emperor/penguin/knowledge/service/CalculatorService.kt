package jp.co.emperor.penguin.knowledge.service

import io.grpc.stub.StreamObserver
import jp.co.emperor.penguin.knowledge.proto.CalculatorGrpc
import jp.co.emperor.penguin.knowledge.proto.CalculatorOuterClass
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class CalculatorService : CalculatorGrpc.CalculatorImplBase() {
    override fun add(
        request: CalculatorOuterClass.AdditionRequest?,
        responseObserver: StreamObserver<CalculatorOuterClass.AdditionReply>?
    ) {
        val replyBuilder = CalculatorOuterClass.AdditionReply.newBuilder().setResult((request?.added?.plus(request?.add)) ?: 0)
        responseObserver?.onNext(replyBuilder.build())
        responseObserver?.onCompleted()
    }
}