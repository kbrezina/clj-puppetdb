(ns clj-puppetdb.util
  (:import [java.io File]
           [java.net URL]))

(defn file?
  [^URL f]
  (if (nil? f)
    false
    (-> f .toURI File. .isFile)))