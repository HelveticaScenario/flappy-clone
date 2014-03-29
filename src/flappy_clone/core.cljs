(ns flappy-clone.core
  :require [monet.canvas :as canvas])

(enable-console-print!)
(println "Hello world!")

(def canvas-dom (.getElement js/document "#canvas"))
