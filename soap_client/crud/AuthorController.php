<?php
  $soap_request_author = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://soapserver/api/author-ws\">\n";
  $soap_request_author .= "<soapenv:Header/>";
  $soap_request_author .= "  <soapenv:Body>";
  $soap_request_author .= "    <ns:getAllAuthorRequest/>";
  $soap_request_author .= "  </soapenv:Body>\n";
  $soap_request_author .= "</soapenv:Envelope>";

  $headers = array(
    "Content-type: text/xml;charset=\"utf-8\"",
    "Accept: text/xml",
    "Cache-Control: no-cache",
    "Pragma: no-cache",
    "SOAPAction: http://localhost:8080/ws/author", 
    "Content-length: ".strlen($soap_request_author),
);

  $soap_do = curl_init();
  curl_setopt($soap_do, CURLOPT_URL, "http://localhost:8080/ws/author");
  curl_setopt($soap_do, CURLOPT_POST, true);
  curl_setopt($soap_do, CURLOPT_RETURNTRANSFER, 1);
  curl_setopt($soap_do, CURLOPT_HTTPAUTH, CURLAUTH_ANY);
  curl_setopt($soap_do, CURLOPT_TIMEOUT, 10);
  curl_setopt($soap_do, CURLOPT_POSTFIELDS, $soap_request_author); // the SOAP request
  curl_setopt($soap_do, CURLOPT_HTTPHEADER, $headers);

  $response_author = curl_exec($soap_do);
  if($response_author === false) {
    $err = 'Curl error: ' . curl_error($soap_do);
    curl_close($soap_do);
    print $err;
  } else {
    curl_close($soap_do);
    $response_author = str_replace("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/><SOAP-ENV:Body>", "", $response_author);
    $response_author = str_replace("</SOAP-ENV:Body></SOAP-ENV:Envelope>", "", $response_author);
    $response_author = str_replace("xmlns:ns2=\"http://soapserver/api/author-ws\"", "", $response_author);
    $response_author = str_replace(":", "", $response_author);
    //echo $response;
    $xmlObject = simplexml_load_string(strval($response_author));
    $jsonString = json_encode($xmlObject);
    $jsonAuthorArray = json_decode($jsonString, true);
    //var_dump($jsonAuthorArray);
  }
?>