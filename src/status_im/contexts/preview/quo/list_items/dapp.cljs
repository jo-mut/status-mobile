(ns status-im.contexts.preview.quo.list-items.dapp
  (:require
    [quo.core :as quo]
    [quo.foundations.resources :as resources]
    [react-native.core :as rn]
    [reagent.core :as reagent]
    [status-im.contexts.preview.quo.preview :as preview]))

(def descriptor
  [{:key     :state
    :type    :select
    :options [{:key   :default
               :value "Default"}
              {:key   :active
               :value "Active"}]}
   {:key     :dapp
    :type    :select
    :options [{:value "With icon"
               :key   {:avatar (resources/get-dapp :coingecko)
                       :name   "Coingecko"
                       :value  "coingecko.com"}}
              {:value "Without icon"
               :key   {:avatar              nil
                       :name                "Coingecko"
                       :value               "coingecko.com"
                       :customization-color :blue}}]}])

(defn preview
  []
  (let [state (reagent/atom {:dapp                {:avatar (resources/get-dapp :coingecko)
                                                   :name   "Coingecko"
                                                   :value  "coingecko.com"}
                             :state               :default
                             :blur?               false
                             :customization-color :blue
                             :on-press            (fn [{:keys [name]}]
                                                    (js/alert (str name " got pressed")))})]
    (fn []
      [preview/preview-container {:state state :descriptor descriptor}
       [rn/view
        {:padding-vertical 60
         :flex-direction   :row
         :justify-content  :center}
        [quo/dapp @state]]])))
