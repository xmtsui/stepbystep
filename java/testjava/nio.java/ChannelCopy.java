package com.tsui.testnio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
  * Test copying between channels.
  * 根据底层文件句柄的访问模式,通道实例可能不允许使用 read()或 write()方 法。
  * @author Ron Hitchens (ron@ronsoft.com)
  */
public class ChannelCopy {
    /**
       * This code copies data from stdin to stdout. Like the 'cat'
       * command, but without any useful options.
       */
    public static void main(String[] argv) throws IOException {
        ReadableByteChannel source = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);
        channelCopy1(source, dest);
        // alternatively, call channelCopy2 (source, dest);
        source.close();
        dest.close();
    }

    /**
      * Channel copy method 1. This method copies data from the src
      * channel and writes it to the dest channel until EOF on src.
      * This implementation makes use of compact( ) on the temp buffer
      * to pack down the data if the buffer wasn't fully drained. This
      * may result in data copying, but minimizes system calls. It also
      * requires a cleanup loop to make sure all the data gets sent.
      */
    private static void channelCopy1(ReadableByteChannel src, WritableByteChannel dest)
                                                                                       throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(buffer) != -1) {
            // Prepare the buffer to be drained
            buffer.flip();
            // Write to the channel; may block
            dest.write(buffer);
            // If partial transfer, shift remainder down
            // If buffer is empty, same as doing clear( )
            buffer.compact();
        }
        // EOF will leave buffer in fill state
        buffer.flip();
        // Make sure that the buffer is fully drained
        while (buffer.hasRemaining()) {
            dest.write(buffer);
        }
    }

    /**
     * Channel copy method 2. This method performs the same copy, but
     * assures the temp buffer is empty before reading more data. This
     * never requires data copying but may result in more systems calls.
     * No post-loop cleanup is needed because the buffer will be empty
     * when the loop is exited.
     */
    @SuppressWarnings("unused")
    private static void channelCopy2(ReadableByteChannel src, WritableByteChannel dest)
                                                                                       throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(buffer) != -1) {
            // Prepare the buffer to be drained
            buffer.flip();
            // Make sure that the buffer was fully drained
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
            // Make the buffer empty, ready for filling
            buffer.clear();
        }
    }
}