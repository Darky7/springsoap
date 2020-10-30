<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Soap Server Client</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/blog-home.css" rel="stylesheet">

</head>

<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="#">Soap Server Client</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" href="#">Author
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Book</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Content -->
  <div class="container">

    <div class="row">

      <!-- Entries Column -->
      <div class="col-md-8">

        <h1 class="my-4">Soap Client
          <small>Api Example</small>
        </h1>

        <!-- Author Post -->
        <div class="card mb-4">
          <div class="card-body">
            <h2 class="card-title">Author List</h2>
            
            <table class="table">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Firstname</th>
                  <th scope="col">Lastname</th>
                  <th scope="col">Handle</th>
                </tr>
              </thead>
              <tbody>
                
                <?php include_once('crud/AuthorController.php'); foreach($jsonAuthorArray['ns2authorType'] AS $key => $el) { ?>
                
                <tr>
                  <th scope="row">1</th>
                  <td><?php echo $el['ns2firstname']; ?></td>
                  <td><?php echo $el['ns2lastname']; ?></td>
                  <td>@author</td>
                </tr>

                <?php } ?>

              </tbody>
            </table>

            <a href="#" class="btn btn-primary">Add More &rarr;</a>
          </div>
        </div>

         <!-- Author Post -->
         <div class="card mb-4">
          <div class="card-body">
            <h2 class="card-title">Book List</h2>
            
            <table class="table">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Title</th>
                  <th scope="col">Isbn</th>
                  <th scope="col">Author</th>
                </tr>
              </thead>
              <tbody>
                
              <?php include_once('crud/BookController.php');  foreach($jsonBookArray['ns2bookType'] AS $key => $el) { ?>

                <tr>
                  <th scope="row">1</th>
                  <td><?php echo $el['ns2title']; ?></td>
                  <td><?php echo $el['ns2isbn']; ?></td>
                  <td><?php echo $el['ns2authorId']; ?></td>
                </tr>

              <?php } ?>

              </tbody>
            </table>

            <a href="#" class="btn btn-primary">Add More &rarr;</a>
          </div>
        </div>

      </div>

      <!-- Sidebar Widgets Column -->
      <div class="col-md-4">

        <!-- Categories Widget -->
        <div class="card my-4">
          <h5 class="card-header">Categories</h5>
          <div class="card-body">
            <div class="row">
              <div class="col-lg-6">
                <ul class="list-unstyled mb-0">
                  <li>
                    <a href="#">Web Design</a>
                  </li>
                  <li>
                    <a href="#">HTML</a>
                  </li>
                  <li>
                    <a href="#">Freebies</a>
                  </li>
                </ul>
              </div>
              <div class="col-lg-6">
                <ul class="list-unstyled mb-0">
                  <li>
                    <a href="#">JavaScript</a>
                  </li>
                  <li>
                    <a href="#">CSS</a>
                  </li>
                  <li>
                    <a href="#">Tutorials</a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>

        <!-- Side Widget -->
        <div class="card my-4">
          <h5 class="card-header">Side Widget</h5>
          <div class="card-body">
            You can put anything you want inside of these side widgets. They are easy to use, and feature the new Bootstrap 4 card containers!
          </div>
        </div>

      </div>

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Soap Server Client 2020</p>
    </div>
    <!-- /.container -->
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
