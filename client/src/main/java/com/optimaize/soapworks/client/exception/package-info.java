/**
 * Provides exception classes that are thrown by this software.
 *
 * <p>All exceptions are RuntimeExceptions and subclasses of ServiceException. The amount of different
 * exception subclasses is kept to a minimum. The classes are named by type of error, and contain
 * a text message for the human as well as the FaultInfo object for the machine to act based
 * on the exception.</p>
 *
 * <p>Exceptions Concept:
 * Exceptions can be categorized by:
 * <ol>
 *   <li>What's the kind/category of exception?</li>
 *   <li>What's the exact cause?</li>
 *   <li>Who throws it?: server, client</li>
 *   <li>Who's to blame?</li>
 *   <li>Can I try again?</li>
 *   <li>What are the next steps?</li>
 * </ol>
 * This software attempts to answer the basic questions in the following way:
 * <ol>
 *   <li>In the class name and class inheritance. Catch blocks may react to this.</li>
 *   <li>In text form, see {@link ServiceException#getMessage()}. This information is meant for humans.</li>
 *   <li>(TODO currently not answered)</li>
 *   <li>See {@link FaultInfo#getBlame()} basically one of server, client, network, unknown</li>
 *   <li>See {@link FaultInfo#getRetry()}</li>
 *   <li>See {@link FaultInfo#isProblemLoggedOnServer()}</li>
 * </ol>
 * </p>
 *
 * <p>Interceptors (in the form of a ModeExtension) may catch exceptions and act based on them,
 * such as log or try again.</p>
 *
 */
package com.optimaize.soapworks.client.exception;
