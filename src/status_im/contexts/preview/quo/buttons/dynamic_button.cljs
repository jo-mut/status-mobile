(ns status-im.contexts.preview.quo.buttons.dynamic-button
  (:require
    [quo.core :as quo]
    [reagent.core :as reagent]
    [status-im.contexts.preview.quo.preview :as preview]
    [utils.i18n :as i18n]))

(def descriptor
  [{:key     :type
    :type    :select
    :options [{:key :mention}
              {:key :notification-down}
              {:key :notification-up}
              {:key :search}
              {:key :search-with-label}
              {:key :scroll-to-bottom}]}
   {:key  :count
    :type :text}])

(defn view
  []
  (let [state (reagent/atom {:count  "5"
                             :type   :search-with-label
                             :labels {:search-with-label (i18n/label :t/back)}})]
    (fn []
      [preview/preview-container
       {:state                     state
        :descriptor                descriptor
        :component-container-style {:align-items :center}}
       [quo/dynamic-button @state]])))
