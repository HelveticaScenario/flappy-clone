(ns flappy-clone.core
  (:require [goog.events :as events]
            [goog.dom :as dom]
            [monet.canvas :as canvas]))

(def surface (canvas/init (.getElementById js/document "surface") "2d"))
(def params {:width 600
             :height 600})

(defn add-box [{:keys [id color x y w h]}]
  (canvas/add-entity surface id
                     (canvas/entity {:x x :y y :w w :h h} ; val
                                    nil                   ; update function
                                    (fn [ctx val]         ; draw function
                                      (-> ctx
                                          (canvas/fill-style color)
                                          (canvas/fill-rect val))))))

(add-box {:id :background :color "#191d21" :x 0 :y 0 :w (params :width) :h (params :height)})
(add-box {:id :bird :color "white" :x 0 :y (/ (params :height) 2) :w 50 :h 50})
(add-box {:id :pipe :color "green" :x (/ (params :width) 2) :y 0 :w 50 :h 100})
(add-box {:id :pipe1 :color "green" :x (/ (params :width) 3) :y (- (params :height) 100) :w 50 :h 100})
