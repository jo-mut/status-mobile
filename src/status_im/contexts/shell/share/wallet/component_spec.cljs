(ns status-im.contexts.shell.share.wallet.component-spec
  (:require
    [status-im.contexts.shell.share.wallet.view :as wallet-view]
    status-im.contexts.wallet.events
    [test-helpers.component :as h]))

(defn render-wallet-view
  []
  (let [component-rendered (h/render [wallet-view/wallet-tab])
        rerender-fn        (h/get-rerender-fn component-rendered)
        share-qr-code      (h/get-by-label-text :share-qr-code)]
    ;; Fires on-layout since it's needed to render the content
    (h/fire-event :layout share-qr-code #js {:nativeEvent #js {:layout #js {:width 500}}})
    (rerender-fn [wallet-view/wallet-tab])
    (h/fire-event :press (h/get-by-label-text :share-qr-code-legacy-tab))))

(h/describe "share wallet addresses"
  (h/setup-restorable-re-frame)
  (h/before-each
   (fn []
     (h/setup-subs {:dimensions/window-width 500
                    :mediaserver/port 200
                    :wallet/accounts [{:address "0x707f635951193ddafbb40971a0fcaab8a6415160"
                                       :name    "Wallet One"
                                       :emoji   "😆"
                                       :color   :blue}]
                    :wallet/preferred-chain-names-for-address #{:eth :oeth :arb1}})))

  (h/test "should display the wallet tab"
    (render-wallet-view)
    (-> (h/expect (h/query-by-text "Wallet One"))
        (h/is-truthy)))

  (h/test "should display the legacy account"
    (render-wallet-view)
    (-> (h/wait-for #(h/get-by-label-text :share-qr-code-legacy-tab))
        (.then (fn []
                 (h/is-truthy (h/query-by-text "0x707f635951193ddafbb40971a0fcaab8a6415160"))
                 (h/is-falsy (h/query-by-text "eth:"))))))

  ;; NOTE: Fails with error below possibly due to Infura outage:
  ;;    FAIL  ./status_im.contexts.shell.share.wallet.component_spec.js
  ;;  ● share wallet addresses › should display the multichain account
  ;;
  ;;   No protocol method IDeref.-deref defined for type undefined
  (h/test-skip "should display the multichain account"
    (render-wallet-view)
    (-> (h/wait-for #(h/get-by-label-text :share-qr-code-multichain-tab))
        (.then (fn []
                 (h/fire-event :press (h/get-by-label-text :share-qr-code-multichain-tab))
                 (-> (h/wait-for #(h/get-by-label-text :share-qr-code-settings))
                     (.then (fn []
                              (h/is-truthy (h/get-by-text "eth:"))
                              (h/is-truthy (h/get-by-text "oeth:"))
                              (h/is-truthy (h/get-by-text "arb1:"))))))))))
