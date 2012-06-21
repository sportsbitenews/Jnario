/*******************************************************************************
 * Copyright (c) 2012 BMW Car IT and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.jnario.spec.tests.integration
import static org.jnario.lib.JnarioCollectionLiterals.*


describe "CollectionLiterals"{

	describe "List Literal"{
		fact list("green", "red") => newArrayList("green", "red")
		fact list(list("green"), list("red")) => newArrayList(newArrayList("green"), newArrayList("red"))
	}
	
	describe "Set Literal"{
		fact set("green", "red") => newHashSet("green", "red")
		fact set(set("green"), set("red")) => newHashSet(newHashSet("green"), newHashSet("red"))
	}    
			
}