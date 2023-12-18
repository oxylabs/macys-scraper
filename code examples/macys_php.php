<?php

$params = [
    'source' => 'universal',
    'url' => 'https://www.macys.com/shop/sale?id=3536&trackingid=407x733169&m_sc=sem&m_sb=google&m_tp=trademark&m_ac=google_trademark_international&m_ag=macy%27score_exact&m_cn=ggl_trademark_intl_lithuania_exact&m_pi=go_cmp-94807774_adg-154238318312_ad-674544461209_kwd-252677959_dev-c_ext-102882313401_prd-&gad_source=1&gclid=cj0kcqiayewrbhddarisagp1mwsg3z6ogoqrztdycjyqio5togc316ldkwuqkkbhrmiv4i_ho0gcjlkaagl6ealw_wcb'];

$ch = curl_init();

curl_setopt($ch, CURLOPT_URL, "https://realtime.oxylabs.io/v1/queries");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($params));
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_USERPWD, "user" . ":" . "pass1");

$headers = array();
$headers[] = "Content-Type: application/json";
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

$result = curl_exec($ch);
echo $result;

if (curl_errno($ch)) {
    echo 'Error:' . curl_error($ch);
}
curl_close ($ch);
?>