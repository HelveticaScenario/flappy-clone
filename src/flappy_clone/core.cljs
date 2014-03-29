(ns flappy-clone.core
  (:require [monet.canvas :as canvas]))

(enable-console-print!)

(def canvas-dom (.getElementById js/document "surface"))

(def monet-canvas (canvas/init canvas-dom "2d"))

(canvas/add-entity monet-canvas :background
                   (canvas/entity {:x 0 :y 0 :w 600 :h 600} ; val
                                  nil                       ; update function
                                  (fn [ctx val]             ; draw function
                                    (-> ctx
                                        (canvas/fill-style "#191d21")
                                        (canvas/fill-rect val)))))

