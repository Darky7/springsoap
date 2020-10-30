<?php
  $soap_request_book = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://soapserver/api/book-ws\">\n";
  $soap_request_book .= "<soapenv:Header/>";
  $soap_request_book .= "  <soapenv:Body>";
  $soap_request_book .= "    <ns:getAllBookRequest/>";
  $soap_request_book .= "  </soapenv:Body>\n";
  $soap_request_book .= "</soapenv:Envelope>";

  $headers_book = array(
    "Content-type: text/xml;charset=\"utf-8\"",
    "Accept: text/xml",
    "Cache-Control: no-cache",
    "Pragma: no-cache",
    "SOAPAction: http://localhost:8080/ws/book", 
    "Content-length: ".strlen($soap_request_book),
);

  $soap_do_book = curl_init();
  curl_setopt($soap_do_book, CURLOPT_URL, "http://localhost:8080/ws/book");
  curl_setopt($soap_do_book, CURLOPT_POST, true);
  curl_setopt($soap_do_book, CURLOPT_RETURNTRANSFER, 1);
  curl_setopt($soap_do_book, CURLOPT_HTTPAUTH, CURLAUTH_ANY);
  curl_setopt($soap_do_book, CURLOPT_TIMEOUT, 10);
  curl_setopt($soap_do_book, CURLOPT_POSTFIELDS, $soap_request_book); // the SOAP request
  curl_setopt($soap_do_book, CURLOPT_HTTPHEADER, $headers_book);

  $response_book = curl_exec($soap_do_book);
  if($response_book === false) {
    $err = 'Curl error: ' . curl_error($soap_do_book);
    curl_close($soap_do_book);
    print $err;
  } else {
    curl_close($soap_do_book);
    $response_book = str_replace("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/><SOAP-ENV:Body>", "", $response_book);
    $response_book = str_replace("</SOAP-ENV:Body></SOAP-ENV:Envelope>", "", $response_book);
    $response_book = str_replace("xmlns:ns2=\"http://soapserver/api/book-ws\"", "", $response_book);
    $response_book = str_replace(":", "", $response_book);
    //echo $response;
    $xmlObject_book = simplexml_load_string(strval($response_book));
    $jsonStringBook = json_encode($xmlObject_book);
    echo $xmlObject_book;
    $jsonBookArray = json_decode($jsonStringBook, true);
    //var_dump($jsonStringBook);
  }
?>