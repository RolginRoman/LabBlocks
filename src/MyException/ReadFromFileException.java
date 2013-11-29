/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MyException;

import java.io.IOException;


/**
 *
 * @author irolg_000
 */
public class ReadFromFileException extends IOException {

    /**
     * Creates a new instance of <code>ReadFromFileException</code> without
     * detail message.
     */
    public ReadFromFileException() {
    }

    /**
     * Constructs an instance of <code>ReadFromFileException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ReadFromFileException(String msg) {
        super(msg);
    }
}
