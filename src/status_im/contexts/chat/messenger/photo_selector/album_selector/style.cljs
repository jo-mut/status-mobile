(ns status-im.contexts.chat.messenger.photo-selector.album-selector.style
  (:require
    [quo.foundations.colors :as colors]
    [react-native.reanimated :as reanimated]))

(defn selector-container
  [top theme]
  (reanimated/apply-animations-to-style {:top top}
                                        {:position         :absolute
                                         :z-index          1
                                         :background-color (colors/theme-colors
                                                            colors/white
                                                            colors/neutral-100
                                                            theme)
                                         :left             0
                                         :right            0}))

(defn album-container
  [selected?]
  {:flex-direction     :row
   :padding-horizontal 12
   :padding-vertical   8
   :margin-horizontal  8
   :border-radius      12
   :align-items        :center
   :background-color   (when selected? colors/primary-50-opa-5)})

(def cover
  {:width         40
   :height        40
   :border-radius 10})

(def divider
  {:padding-horizontal 20
   :margin-top         12
   :margin-bottom      8})
