(ns legacy.status-im.ui.screens.sync-settings.views
  (:require-macros [legacy.status-im.utils.views :as views])
  (:require
    [legacy.status-im.ui.components.colors :as colors]
    [legacy.status-im.ui.components.core :as components]
    [legacy.status-im.ui.components.list.item :as list.item]
    [legacy.status-im.ui.components.react :as react]
    [quo.core :as quo]
    [re-frame.core :as re-frame]
    [status-im.constants :as constants]
    [utils.i18n :as i18n]
    [utils.re-frame :as rf]))

(views/defview sync-settings
  []
  (views/letsubs [{:keys [backup-enabled?
                          default-sync-period
                          use-mailservers?]}
                  [:profile/profile]
                  current-mailserver-name [:mailserver/current-name]]
    [:<>
     [quo/page-nav
      {:type       :no-title
       :background :blur
       :icon-name  :i/close
       :on-press   #(rf/dispatch [:navigate-back])}]
     [react/scroll-view
      [components/list-header (i18n/label :t/data-syncing)]
      [list.item/list-item
       {:size                :small
        :title               (i18n/label :t/backup-settings)
        :accessibility-label :backup-settings-button
        :on-press            #(re-frame/dispatch [:navigate-to :backup-settings])
        :chevron             true
        :accessory           :text
        :accessory-text      (if backup-enabled?
                               (i18n/label :t/backup-enabled)
                               (i18n/label :t/backup-disabled))}]
      [list.item/list-item
       {:size                :small
        :title               (i18n/label :t/default-sync-period)
        :accessibility-label :default-sync-period-button
        :on-press            #(re-frame/dispatch [:navigate-to :default-sync-period-settings])
        :chevron             true
        :accessory           :text
        :accessory-text      (cond
                               (= default-sync-period constants/two-mins)
                               (i18n/label :t/two-minutes)

                               (or
                                (nil? default-sync-period)
                                (= default-sync-period constants/one-day))
                               (i18n/label :t/one-day)

                               (= default-sync-period constants/three-days)
                               (i18n/label :t/three-days)

                               (= default-sync-period constants/nine-days)
                               (i18n/label :t/nine-days)

                               (= default-sync-period constants/one-week)
                               (i18n/label :t/one-week)

                               (= default-sync-period constants/one-month)
                               (i18n/label :t/one-month))}]
      [list.item/list-item
       {:size                :small
        :accessibility-label :offline-messages-settings-button
        :title               (i18n/label :t/history-nodes)
        :on-press            #(re-frame/dispatch [:navigate-to :offline-messaging-settings])
        :accessory           :text
        :accessory-text      (when use-mailservers? current-mailserver-name)
        :chevron             true}]
      ;; TODO(Ferossgp): Devider componemt
      [react/view
       {:height           1
        :background-color colors/gray-lighter
        :margin-top       8}]
      [components/list-header (i18n/label :t/device-syncing)]
      [list.item/list-item
       {:size                :small
        :title               (i18n/label :t/devices)
        :accessibility-label :pairing-settings-button
        :on-press            #(re-frame/dispatch [:navigate-to :installations])
        :chevron             true}]]]))
