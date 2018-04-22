package pl.ipebk.schibsted.remote

/**
 * Interface to provide information about Internet availability to remote layer
 */
interface ConnectionChecker {
  fun isInternetConnectionAvailable(): Boolean
}