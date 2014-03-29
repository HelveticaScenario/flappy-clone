(ns flappy-clone.core
  (:require [goog.events :as events]
            [goog.dom :as dom]
            [monet.canvas :as canvas]))

(def surface (canvas/init (.getElementById js/document "surface") "2d"))

(canvas/add-entity surface :background
                   (canvas/entity {:x 0 :y 0 :w 600 :h 600} ; val
                                  nil                       ; update function
                                  (fn [ctx val]             ; draw function
                                    (-> ctx
                                        (canvas/fill-style "#191d21")
                                        (canvas/fill-rect val)))))
