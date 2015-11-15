package org.thoughtworks.assessment.merchant.factory.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Optional;

import org.thoughtworks.assessment.merchant.processor.Merchant;
import org.thoughtworks.assessment.merchant.processor.common.types.Reply;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;

/**
 * Provides the user interface to the program over the command line interface (CLI).
 * 
 * Gets the input data from the system input and puts the program output into the system output.
 */
public final class MerchantCli {

	/**
	 * Entry point for the process.
	 */
	public static void main(final String[] args) {

		try(
				final Reader in = new InputStreamReader(System.in);
				final Writer out = new OutputStreamWriter(System.out) ){

			process(in, out);
		}
		catch(final IOException e){
			throw new RuntimeException("unexpected problem during IO operation", e);
		}

	}

    /**
     * Processes the requests.
     *
     * @param in an input stream the requests are read out.
     * @param out an output stream the replies are written to.
     */
	public static void process(final Reader in, final Writer out) {

		final BufferedReader bin = new BufferedReader(in);

		final Merchant merchant = new Merchant();

		Optional<Request> request;

		while( (request=readNextRequest(bin)).isPresent() ){

			final Optional<Reply> reply = merchant.process(request.get());
			writeReply(reply, out);
		}
	}

	/**
	 * Read out the next request from the input stream.
	 * Returns empty if there in no more data.
	 */
	private static Optional<Request> readNextRequest(final BufferedReader in){
		try {

			final String line = in.readLine();
			return line != null ? Optional.of(new Request(line)) : Optional.empty();

		} catch (final IOException e) {
			throw new RuntimeException("unable to read from input.", e);
		}
	}

	/**
	 * Writes the reply to the output stream. 
	 */
	private static void writeReply(final Optional<Reply> reply, final Writer out){
		if(reply.isPresent()) try {
			out.write(reply.get().getValue());
			out.write('\n');
			out.flush();
		} catch (final IOException e) {
			throw new RuntimeException("unable to write out respose.", e);
		}
	}

}
