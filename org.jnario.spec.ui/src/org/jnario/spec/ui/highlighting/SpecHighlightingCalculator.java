/*******************************************************************************
 * Copyright (c) 2012 BMW Car IT and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.jnario.spec.ui.highlighting;

import static org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration.KEYWORD_ID;

import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.ui.highlighting.XbaseHighlightingConfiguration;
import org.eclipse.xtext.xtend2.ui.highlighting.XtendHighlightingCalculator;
import org.eclipse.xtext.xtend2.xtend2.Xtend2Package;
import org.eclipse.xtext.xtend2.xtend2.XtendField;
import org.eclipse.xtext.xtend2.xtend2.XtendFunction;
import org.eclipse.xtext.xtend2.xtend2.XtendMember;
import org.jnario.ExampleTable;
import org.jnario.JnarioPackage;
import org.jnario.spec.spec.Example;
import org.jnario.spec.spec.ExampleGroup;
import org.jnario.spec.spec.SpecFile;
import org.jnario.spec.spec.SpecPackage;

/**
 * @author Sebastian Benz - Initial contribution and API
 */
@SuppressWarnings("restriction")
public class SpecHighlightingCalculator extends XtendHighlightingCalculator {

	@Override
	protected void doProvideHighlightingFor(XtextResource resource, IHighlightedPositionAcceptor acceptor) {
		SpecFile file = (SpecFile) resource.getContents().get(0);
		ExampleGroup exampleGroup = (ExampleGroup) file.getXtendClass();
		highlightDeprectedXtendAnnotationTarget(acceptor, exampleGroup);
		provideHighlightingFor(exampleGroup, acceptor);
		super.doProvideHighlightingFor(resource, acceptor);
	}

	protected void provideHighlightingFor(ExampleGroup exampleGroup,
			IHighlightedPositionAcceptor acceptor) {
		if (exampleGroup != null) {
			for (XtendMember member : exampleGroup.getMembers()) {
				if (member.eClass() == Xtend2Package.Literals.XTEND_FUNCTION) {
					XtendFunction function = (XtendFunction) member;
					XExpression rootExpression = function.getExpression();
					highlightRichStrings(rootExpression, acceptor);
				}else if(member.eClass() == Xtend2Package.Literals.XTEND_FIELD){
					XtendField field = (XtendField) member;
					highlightXtendField(field,acceptor);
				}else if(member.eClass() == SpecPackage.Literals.EXAMPLE){
					Example example = (Example) member;
					highlightObjectAtFeature(acceptor, example, SpecPackage.Literals.EXAMPLE__PREAMBLE, KEYWORD_ID);
					highlightRichStrings(example.getBody() ,acceptor);
				}else if(member.eClass() == SpecPackage.Literals.EXAMPLE_GROUP){
					ExampleGroup subExampleGroup = (ExampleGroup) member;
					provideHighlightingFor(subExampleGroup,acceptor);
				}else if(member.eClass() == JnarioPackage.Literals.EXAMPLE_TABLE){
					provideHighlightingFor((ExampleTable)member,acceptor);
				}
				highlightDeprectedXtendAnnotationTarget(acceptor, member);
			}
		}
	}

	private void provideHighlightingFor(ExampleTable exampleHeading, IHighlightedPositionAcceptor acceptor) {
		if(exampleHeading == null){
			return;
		}
		for (XtendField element : exampleHeading.getColumns()) {
			INode node = NodeModelUtils.getNode(element);
			highlightNode(node , XbaseHighlightingConfiguration.EXTENSION_METHOD_INVOCATION, acceptor);
		}
	}
}