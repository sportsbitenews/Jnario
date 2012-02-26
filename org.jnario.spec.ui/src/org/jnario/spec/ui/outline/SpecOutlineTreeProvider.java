/*******************************************************************************
 * Copyright (c) 2012 BMW Car IT and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.jnario.spec.ui.outline;

import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtend.ide.outline.XtendOutlineTreeProvider;
import org.eclipse.xtend.core.xtend.XtendClass;
import org.eclipse.xtend.core.xtend.XtendMember;

import org.jnario.spec.spec.ExampleGroup;

/**
 * @author Sebastian Benz - Initial contribution and API
 */
@SuppressWarnings("restriction")
public class SpecOutlineTreeProvider extends XtendOutlineTreeProvider {

	@Override
	protected void createFeatureNodes(IOutlineNode parentNode, XtendClass xtendClass) {
		for (XtendMember member : xtendClass.getMembers())
			createEObjectNode(parentNode, member);
	}
	
	protected boolean _isLeaf(ExampleGroup element) {
		return element.getMembers().isEmpty();
	}
	
}
