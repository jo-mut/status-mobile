(ns status-im.contexts.wallet.home.tabs.view
  (:require
    [react-native.core :as rn]
    [status-im.contexts.wallet.collectible.options.view :as options-drawer]
    [status-im.contexts.wallet.common.activity-tab.view :as activity]
    [status-im.contexts.wallet.common.collectibles-tab.view :as collectibles]
    [status-im.contexts.wallet.home.tabs.assets.view :as assets]
    [status-im.contexts.wallet.home.tabs.style :as style]
    [utils.re-frame :as rf]))

(defn- on-collectible-long-press
  [{:keys [preview-url :collectible-data id]}]
  (rf/dispatch [:show-bottom-sheet
                {:content (fn []
                            [options-drawer/view
                             {:id    id
                              :name  (:name collectible-data)
                              :image (:uri preview-url)}])}]))

(def on-collectible-press #(rf/dispatch [:wallet/navigate-to-collectible-details %]))

(def request-collectibles #(rf/dispatch [:wallet/request-collectibles-for-all-accounts {}]))

(defn- collectibles-tab
  []
  (let [updating?        (rf/sub [:wallet/home-tab-collectibles-updating?])
        collectible-list (rf/sub [:wallet/owned-collectibles-list-in-selected-networks])]
    [collectibles/view
     {:loading?                  updating?
      :collectibles              collectible-list
      :on-collectible-long-press on-collectible-long-press
      :on-end-reached            request-collectibles
      :on-collectible-press      on-collectible-press}]))

(defn view
  [{:keys [selected-tab]}]
  [rn/view {:style style/container}
   (case selected-tab
     :assets       [assets/view]
     :collectibles [collectibles-tab]
     [activity/view {:activities []}])])
