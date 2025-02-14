(ns status-im.contexts.communities.actions.leave.view
  (:require
    [quo.core :as quo]
    [react-native.core :as rn]
    [status-im.contexts.communities.actions.generic-menu.view :as generic-menu]
    [status-im.contexts.communities.actions.leave.style :as style]
    [utils.i18n :as i18n]
    [utils.re-frame :as rf]))

(defn hide-sheet-and-dispatch
  [event]
  (rf/dispatch [:hide-bottom-sheet])
  (rf/dispatch event))

(defn leave-sheet
  [id color]
  [generic-menu/view
   {:id    id
    :title (i18n/label :t/leave-community?)}
   [:<>
    [quo/text
     {:accessibility-label :communities-join-community
      :size                :paragraph-1
      :style               style/text}
     (i18n/label :t/leave-community-message)]
    [rn/view
     {:style style/button-container}
     [quo/button
      {:on-press        #(rf/dispatch [:hide-bottom-sheet])
       :type            :grey
       :container-style style/cancel-button}
      (i18n/label :t/cancel)]
     [quo/button
      {:on-press            #(hide-sheet-and-dispatch [:communities/leave id])
       :customization-color color
       :container-style     style/action-button}
      (i18n/label :t/leave-community)]]]])

(defn cancel-request-sheet
  [id request-id]
  [generic-menu/view
   {:id    id
    :title (i18n/label :t/cancel-request?)}
   [:<>
    [quo/text
     {:accessibility-label :communities-join-community
      :size                :paragraph-1
      :style               style/text}
     (i18n/label :t/if-you-cancel)]
    [rn/view
     {:style style/button-container}
     [quo/button
      {:accessibility-label :cancel-button
       :on-press            #(rf/dispatch [:hide-bottom-sheet])
       :type                :grey
       :container-style     style/cancel-button}
      (i18n/label :t/close)]
     [quo/button
      {:accessibility-label :confirm-button
       :on-press            #(hide-sheet-and-dispatch [:communities/cancel-request-to-join request-id])
       :container-style     style/action-button}
      (i18n/label :t/confirm)]]]])
