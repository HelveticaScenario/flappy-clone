(ns flappy-clone.core
  (:require [goog.events :as events]
            [goog.dom :as dom]
            [monet.canvas :as canvas]))

(defn counter []
	  (let [tick (atom 0)]
	    #(swap! tick inc)))

(defn rand-within-range [{:keys [lower upper]}]
  (+ (rand-int (- (+ upper 1) lower)) lower))

(def surface (canvas/init (.getElementById js/document "surface") "2d"))
(def global {:width 600
              :height 600})

(def default {:pipex 600
               :pipey 0
               :pipew 50
               :space 150})
(def pipe-tick (counter))
(defn gen-pipe-keyword []
  (keyword "pipe" (first (repeatedly pipe-tick))))

(defn pipe [{:keys [lower? height]}]
  (canvas/add-entity surface (gen-pipe-keyword)
                     (canvas/entity {:x (default :pipex) :y (if lower?
                                                               (- (global :height) height)
                                                               (default :pipey))
                                     :w (default :pipew) :h height}
                                    (fn [{:keys [x y h w]}] {:x (- x 1) :y y :h h :w w})
                                    (fn [ctx val]
                                      (-> ctx
                                          (canvas/fill-style "green")
                                          (canvas/fill-rect val))))))
(defn pipe-pair []
  (let [bottom-height (rand-within-range {:lower 100 :upper 400})]
      (pipe {:lower? true :height bottom-height})
      (pipe {:lower? false :height (- (global :height) (+ bottom-height (default :space)))})))

(defn add-box [{:keys [id color x y w h]}]
  (canvas/add-entity surface id
                     (canvas/entity {:x x :y y :w w :h h} ; val
                                    nil                   ; update function
                                    (fn [ctx val]         ; draw function
                                      (-> ctx
                                          (canvas/fill-style color)
                                          (canvas/fill-rect val))))))

(add-box {:id :background :color "#191d21" :x 0 :y 0 :w (global :width) :h (global :height)})
(add-box {:id :bird :color "white" :x 0 :y (/ (global :height) 2) :w 50 :h 50})

(pipe-pair)
;disgusting hack to remove garbage pipes
(map #(canvas/remove-entity surface %)
     (map :key (filter #(< ((% :val) :x) 0)
                       (map (fn [id] {:key id :val (canvas/get-entity surface id)})
                            (filter #(not (nil? (re-matches (re-pattern "^:pipe/\\d*") %)))
                                    (vec (js-keys (surface :entities))))))))
