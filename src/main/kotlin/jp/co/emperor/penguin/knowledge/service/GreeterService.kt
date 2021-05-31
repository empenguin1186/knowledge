package jp.co.emperor.penguin.knowledge.service

import io.grpc.stub.StreamObserver
import jp.co.emperor.penguin.knowledge.proto.GreeterGrpc
import jp.co.emperor.penguin.knowledge.proto.GreeterOuterClass
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class GreeterService : GreeterGrpc.GreeterImplBase() {
    override fun sayHello(request: GreeterOuterClass.HelloRequest?, responseObserver: StreamObserver<GreeterOuterClass.HelloReply>?) {
        val replyBuilder = GreeterOuterClass.HelloReply.newBuilder().setMessage("Hello ${request?.name}")
        responseObserver?.onNext(replyBuilder.build())
        responseObserver?.onCompleted()
    }
}