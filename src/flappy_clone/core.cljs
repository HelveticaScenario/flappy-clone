(ns flappy-clone.core
  (:require [goog.events :as events]
            [goog.dom :as dom]
            [monet.canvas :as canvas]))

(def surface (canvas/init (.getElementById js/document "surface") "2d"))

(canvas/add-entity surface :background
                   (canvas/entity {:x 0 :y 0 :w 60 :h 60} ; val
                                  (fn [{:keys [x y] :as val}]
                                      (assoc val :x (+ x 5) :y (+ y 5)))                       ; update function
                                  (fn [ctx val]             ; draw function
                                    (-> ctx
                                        (canvas/fill-style "#191d21")
                                        (canvas/fill-rect val)))))

