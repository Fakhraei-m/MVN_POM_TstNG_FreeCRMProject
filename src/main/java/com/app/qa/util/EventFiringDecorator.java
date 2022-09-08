//package com.app.qa.util;
//
//import com.google.common.primitives.Primitives;
//
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.Beta;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.decorators.Decorated;
//import org.openqa.selenium.support.decorators.WebDriverDecorator;
//import org.openqa.selenium.support.events.WebDriverListener;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@SuppressWarnings("rawtypes")
//@Beta
//public class EventFiringDecorator extends WebDriverDecorator {
//
//	  private static final Logger logger = Logger.getLogger(EventFiringDecorator.class.getName());
//
//	  private final List<WebDriverListener> listeners;
//
//	  public EventFiringDecorator(WebDriverListener... listeners) {
//	    this.listeners = Arrays.asList(listeners);
//	  }
//
//	  @SuppressWarnings("unchecked")
//	  @Override
//	  public void beforeCall(Decorated target, Method method, Object[] args) {
//	    listeners.forEach(listener -> fireBeforeEvents(listener, target, method, args));
//	    super.beforeCall(target, method, args);
//	  }
//
//	  @SuppressWarnings("unchecked")
//	  @Override
//	  public void afterCall(Decorated target, Method method, Object[] args, Object result) {
//	    super.afterCall(target, method, args, result);
//	    listeners.forEach(listener -> fireAfterEvents(listener, target, method, result, args));
//	  }
//
//	  @SuppressWarnings("unchecked")
//	  @Override
//	  public Object onError(Decorated target, Method method, Object[] args,
//	                        InvocationTargetException e) throws Throwable {
//	    listeners.forEach(listener -> {
//	      try {
//	        listener.onError(target.getOriginal(), method, args, e);
//	      } catch (Throwable t) {
//	        logger.log(Level.WARNING, t.getMessage(), t);
//	      }
//	    });
//	    return super.onError(target, method, args, e);
//	  }
//
//	  private void fireBeforeEvents(WebDriverListener listener, Decorated<?> target, Method method, Object[] args) {
//	    try {
//	      listener.beforeAnyCall(target.getOriginal(), method, args);
//	    } catch (Throwable t) {
//	      logger.log(Level.WARNING, t.getMessage(), t);
//	    }
//
//	    try {
//	      if (target.getOriginal() instanceof WebDriver) {
//	        listener.beforeAnyWebDriverCall((WebDriver) target.getOriginal(), method, args);
//	      } else if (target.getOriginal() instanceof WebElement) {
//	        listener.beforeAnyWebElementCall((WebElement) target.getOriginal(), method, args);
//	      } else if (target.getOriginal() instanceof WebDriver.Navigation) {
//	        listener.beforeAnyNavigationCall((WebDriver.Navigation) target.getOriginal(), method, args);
//	      } else if (target.getOriginal() instanceof Alert) {
//	        listener.beforeAnyAlertCall((Alert) target.getOriginal(), method, args);
//	      } else if (target.getOriginal() instanceof WebDriver.Options) {
//	        listener.beforeAnyOptionsCall((WebDriver.Options) target.getOriginal(), method, args);
//	      } else if (target.getOriginal() instanceof WebDriver.Timeouts) {
//	        listener.beforeAnyTimeoutsCall((WebDriver.Timeouts) target.getOriginal(), method, args);
//	      } else if (target.getOriginal() instanceof WebDriver.Window) {
//	        listener.beforeAnyWindowCall((WebDriver.Window) target.getOriginal(), method, args);
//	      }
//	    } catch (Throwable t) {
//	      logger.log(Level.WARNING, t.getMessage(), t);
//	    }
//
//	    String methodName = createEventMethodName("before", method.getName());
//
//	    int argsLength = args != null ? args.length : 0;
//	    Object[] args2 = new Object[argsLength + 1];
//	    args2[0] = target.getOriginal();
//	    for (int i = 0; i < argsLength; i++) {
//	      args2[i + 1] = args[i];
//	    }
//
//	    Method m = findMatchingMethod(listener, methodName, args2);
//	    if (m != null) {
//	      callListenerMethod(m, listener, args2);
//	    }
//	  }
//
//	  private void fireAfterEvents(WebDriverListener listener, Decorated<?> target, Method method, Object res, Object[] args) {
//	    String methodName = createEventMethodName("after", method.getName());
//
//	    boolean isVoid = method.getReturnType() == Void.TYPE
//	                     || method.getReturnType() == WebDriver.Timeouts.class;
//	    int argsLength = args != null ? args.length : 0;
//	    Object[] args2 = new Object[argsLength + 1 + (isVoid  ? 0 : 1)];
//	    args2[0] = target.getOriginal();
//	    for (int i = 0; i < argsLength; i++) {
//	      args2[i + 1] = args[i];
//	    }
//	    if (! isVoid) {
//	      args2[args2.length - 1] = res;
//	    }
//
//	    Method m = findMatchingMethod(listener, methodName, args2);
//	    if (m != null) {
//	      callListenerMethod(m, listener, args2);
//	    }
//
//	    try {
//	      if (target.getOriginal() instanceof WebDriver) {
//	        listener.afterAnyWebDriverCall((WebDriver) target.getOriginal(), method, args, res);
//	      } else if (target.getOriginal() instanceof WebElement) {
//	        listener.afterAnyWebElementCall((WebElement) target.getOriginal(), method, args, res);
//	      } else if (target.getOriginal() instanceof WebDriver.Navigation) {
//	        listener.afterAnyNavigationCall((WebDriver.Navigation) target.getOriginal(), method, args,
//	                                        res);
//	      } else if (target.getOriginal() instanceof Alert) {
//	        listener.afterAnyAlertCall((Alert) target.getOriginal(), method, args, res);
//	      } else if (target.getOriginal() instanceof WebDriver.Options) {
//	        listener.afterAnyOptionsCall((WebDriver.Options) target.getOriginal(), method, args, res);
//	      } else if (target.getOriginal() instanceof WebDriver.Timeouts) {
//	        listener.afterAnyTimeoutsCall((WebDriver.Timeouts) target.getOriginal(), method, args, res);
//	      } else if (target.getOriginal() instanceof WebDriver.Window) {
//	        listener.afterAnyWindowCall((WebDriver.Window) target.getOriginal(), method, args, res);
//	      }
//	    } catch (Throwable t) {
//	      logger.log(Level.WARNING, t.getMessage(), t);
//	    }
//
//	    try {
//	      listener.afterAnyCall(target.getOriginal(), method, args, res);
//	    } catch (Throwable t) {
//	      logger.log(Level.WARNING, t.getMessage(), t);
//	    }
//	  }
//
//	  private String createEventMethodName(String prefix, String originalMethodName) {
//	    return prefix + originalMethodName.substring(0, 1).toUpperCase() + originalMethodName.substring(1);
//	  }
//
//	  private Method findMatchingMethod(WebDriverListener listener, String methodName, Object[] args) {
//	    for (Method m : listener.getClass().getMethods()) {
//	      if (m.getName().equals(methodName) && parametersMatch(m, args)) {
//	        return m;
//	      }
//	    }
//	    return null;
//	  }
//
//	  private boolean parametersMatch(Method m, Object[] args) {
//	    Class<?>[] params = m.getParameterTypes();
//	    if (params.length != args.length) {
//	      return false;
//	    }
//	    for (int i = 0; i < params.length; i++) {
//	      if (args[i] != null && ! Primitives.wrap(params[i]).isAssignableFrom(args[i].getClass())) {
//	        return false;
//	      }
//	    }
//	    return true;
//	  }
//
//	  private void callListenerMethod(Method m, WebDriverListener listener, Object[] args) {
//	    try {
//	      m.invoke(listener, args);
//	    } catch (Throwable t) {
//	      logger.log(Level.WARNING, t.getMessage(), t);
//	    }
//	  }
//	}
