package myjava.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

public class SelectorTest {

    @Test
    public void open() throws IOException {
        Selector selector = Selector.open();
    }

    @Test
    public void isOpen() throws IOException {
        Selector selector = Selector.open();
        boolean b = selector.isOpen();
    }

    @Test
    public void provider() throws IOException {
        Selector selector = Selector.open();
        SelectorProvider provider = selector.provider();
    }

    @Test
    public void keys() throws IOException {
        Selector selector = Selector.open();
        Set<SelectionKey> keys = selector.keys();
    }

    @Test
    public void selectedKeys() throws IOException {
        Selector selector = Selector.open();
        Set<SelectionKey> keys = selector.selectedKeys();
    }

    @Test
    public void selectNow() throws IOException {
        Selector selector = Selector.open();
        int i = selector.selectNow();
    }

    @Test
    public void select() throws IOException {
        Selector selector = Selector.open();
        int s = selector.select(0);
    }

    @Test
    public void select2() throws IOException {
        Selector selector = Selector.open();
        int s = selector.select();
    }

    @Test
    public void wakeup() throws IOException {
        Selector selector = Selector.open();
        Selector w = selector.wakeup();
    }

    @Test
    public void close() throws IOException {
        Selector selector = Selector.open();
        selector.close();
    }


    //---------------------------------
    @Test
    public void client() throws IOException {
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress(9898));
        sChannel.configureBlocking(false);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put("abc".getBytes());
        buf.flip();
        sChannel.write(buf);
        buf.clear();
        sChannel.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //切换非阻塞模式
        ssChannel.configureBlocking(false);
        ssChannel.bind(new InetSocketAddress(9898));

        Selector selector = Selector.open();
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //轮询
        while (selector.select() > 0) {
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while (it.hasNext()) {
                SelectionKey sk = it.next();

                if (sk.isAcceptable()) {
                    SocketChannel sChannel = ssChannel.accept();
                    sChannel.configureBlocking(false);
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = sChannel.read(buf)) > 0) {
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }
                it.remove();
            }
        }
    }
}