/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MyException;

/**
 *
 * @author irolg_000
 */
public class BuildingUnderArrestException extends IllegalStateException {

    /**
     * Creates a new instance of <code>BuildingUnderArrestException</code>
     * without detail message.
     */
    public BuildingUnderArrestException() {
    }

    /**
     * Constructs an instance of <code>BuildingUnderArrestException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public BuildingUnderArrestException(String msg) {
        super(msg);
    }
}
