package websocket;

import static org.jboss.netty.channel.Channels.*;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.http.HttpChunkAggregator;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;

public class WebSocketServerPipelineFactory implements ChannelPipelineFactory {
	public static OrderedMemoryAwareThreadPoolExecutor e = new OrderedMemoryAwareThreadPoolExecutor(16, 0L, 0L);

	private static final ExecutionHandler executionHandler = new ExecutionHandler(e);
	
    public ChannelPipeline getPipeline() throws Exception {
        // Create a default pipeline implementation.
        ChannelPipeline pipeline = pipeline();
        pipeline.addLast("decoder", new HttpRequestDecoder());
        pipeline.addLast("aggregator", new HttpChunkAggregator(65536));
        pipeline.addLast("encoder", new HttpResponseEncoder());
        pipeline.addLast("executor", executionHandler);
        pipeline.addLast("handler", new WebSocketServerHandler());
        return pipeline;
    }
}
