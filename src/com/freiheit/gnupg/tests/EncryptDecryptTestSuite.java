/*
 * $Id: GnuPGTestSuite.java,v 1.3 2006/06/14 15:53:12 sneumann Exp $
 * (c) Copyright 2005 freiheit.com technologies gmbh, Germany.
 *
 * This file is part of Java for GnuPG  (http://www.freiheit.com).
 *
 * Java for GnuPG is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * Please see COPYING for the complete licence.
 */

package com.freiheit.gnupg.tests;

import com.freiheit.gnupg.GnuPGContext;
import com.freiheit.gnupg.GnuPGData;
import com.freiheit.gnupg.GnuPGKey;

import junit.framework.TestCase;

public class EncryptDecryptTestSuite extends TestCase {
	
    public void testDecryptFromOneRecipient() {
    	String myText = "fish are free on saturdays";
        GnuPGContext ctx = new GnuPGContext();
        
        GnuPGData plain = ctx.createDataObject(myText);
        
        GnuPGKey[] recipient = ctx.generateEmptyKeyArray(1);
        
        String SBODNAR_PUBLIC_KEY = System.getenv("COLLIE_PUBLIC_KEY_FINGERPRINT");
        
        recipient[0] = ctx.getKeyByFingerprint(SBODNAR_PUBLIC_KEY);

        GnuPGData cipher = ctx.createDataObject();
        ctx.encrypt(recipient, plain, cipher);
        System.out.println(cipher.toString());
        
        GnuPGData decrypted = ctx.createDataObject();
        ctx.decrypt(cipher, decrypted);
        System.out.println(decrypted.toString());

        assertNotNull(decrypted.toString());
        assertEquals(decrypted.toString(), myText);
    }
}
