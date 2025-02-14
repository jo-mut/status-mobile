(ns quo.components.avatars.token-avatar.view
  (:require [quo.components.avatars.token-avatar.style :as style]
            [quo.components.utilities.token.view :as token]
            [react-native.core :as rn]
            [react-native.hole-view :as hole-view]
            [react-native.platform :as platform]
            [schema.core :as schema]))

(def ?schema
  [:=>
   [:catn
    [:props
     [:map {:closed true}
      [:type {:optional true} [:enum :asset :collectible]]
      [:context? {:optional true} [:maybe :boolean]]
      [:image {:optional true} [:maybe :schema.common/image-source]]
      [:token {:optional true} [:maybe [:or :keyword :string]]]
      [:network-image {:optional true} [:maybe :schema.common/image-source]]
      [:container-style {:optional true} [:maybe :map]]]]]
   :any])

(defn- view-internal
  [{:keys [type context? image token network-image container-style]}]
  [rn/view
   {:style               (merge style/container container-style)
    :accessibility-label :token-avatar}
   [hole-view/hole-view
    (cond-> {:holes (if context?
                      [{:x            19
                        :y            19
                        :width        18
                        :height       18
                        :borderRadius 9}]
                      [])
             :style style/hole-view}
      platform/android? (assoc :key context?))
    [token/view
     {:size         :size-32
      :token        token
      :style        (style/image type)
      :image-source image}]]
   (when context?
     [rn/image
      {:source network-image
       :style  style/context}])])

(def view (schema/instrument #'view-internal ?schema))
