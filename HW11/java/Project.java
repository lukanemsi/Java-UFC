public class Project {
    public static void main(String[] args){
        System.out.println("-------------------------");
        /**
         * task 1,2,3
         * */
        Figure tr = new Triangle(10, 0, 0);
        try {
            // წინა დავალებაში კონსტრუქტორიდან ვისროდი მხოლოდ TriangleValidateException_ს მაგრამ
            // ახლა კონსტრუქტორში აღარ ვისვრი და validate მეთოდს ვასროლინებ ValidateEx_ს რადგან პირობის მიხედვით სამივე Exception_ის დაწერა შემძლებოდა.
            tr.validate();
        } catch (TriangleValidateException e) {
            System.out.println("TriangleValidateException: " + e.getMessage());
        } catch (RectangleValidateException e) {
            System.out.println("RectangleValidateException: " + e.getMessage());
        } catch (ValidateException e) {
            System.out.println("ValidateException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("other Exception: " + e.getMessage());
        }

        System.out.println("-------------------------");
        /**
         * task 4,5
         * */
        try {
            // 11 Triangle -> TriangleLimitException
            for (int i = 0; i < 11; i++) {
                new Triangle(3, 4, 5);
            }
        } catch (TriangleLimitException e)
        {
            System.out.println(e.getMessage());
            // ხელმეორედ გასროლა, ვიჭერ რადგან პროგრამა გაგრძელდეს
            try {
                throw new RuntimeException(e);
            } catch (RuntimeException ex) {
                System.out.println("RETHROW RuntimeException: " + ex.getMessage() + ", " + ex.getCause());
            }
        }
        System.out.println("-------------------------");

        /**
         * task 6,7,8
         * */
        try {
            new Rectangle(1000, 1000);
        }
        catch (AreaTooLargeException e)
        {
            System.out.println(e.getMessage());
            // ხელმეორედ გასროლა, ვიჭერ რადგან პროგრამა გაგრძელდეს
            try
            {
                throw e;
            }
            catch (AreaTooLargeException ex)
            {
                System.out.println("RETHROW AreaTooLargeException: " + e.getMessage());
            }
        }
        finally
        {
            System.out.println("Bye Figures");
        }
        System.out.println("-------------------------");

        /**
         * task 9,10
         * */
        Figure rec = new Rectangle(0,-1);
        // თუ შიდა try_ის rec.validate() ვერ გაიარა და catch_მა შესაბამისი ინსტანსის Exception_ი ვერ დაიჭირა მაშინ მართვა გადაეცემა გარეთა Try_ის და მის catch_ს
        try
        {
            try
            {
                try
                {
                    rec.validate();
                } catch (TriangleValidateException e)
                {
                    System.out.println("Triangle caught!: " + e.getMessage());
                }
            }
            catch (RectangleValidateException e)
            {
                System.out.println("Rectangle caught!: " + e.getMessage());
            }
        }
        catch (ValidateException e)
        {
            System.out.println("ValidateFigure caught!: " + e.getMessage());
        }


        try
        {
            rec.validate();
        } catch (TriangleValidateException e)
        {
            System.out.println("Triangle caught!: " + e.getMessage());
        }catch (RectangleValidateException e)
        {
            System.out.println("Rectangle caught!: " + e.getMessage());
        }catch (ValidateException e)
        {
            System.out.println("ValidateFigure caught!: " + e.getMessage());
        }


        // try_ში ჩადგმული შიდა try,catch_ის საშუალებით
        try
        {
            try{
            rec.validate();
            }catch (ValidateException e)
            {
                System.out.println("Validation Finished Unsuccessfully");
                throw  e;
            }
        }
        catch (ValidateException e) {
            System.out.println(e.getMessage());
        }


        // catch_ში ჩადგმული შიდა try,catch_ის საშუალებით
        try
        {
            rec.validate();
        }
        catch (ValidateException e)
        {
            System.out.println("Validation Finished Unsuccessfully");
            try
            {
                throw e;
            }catch (ValidateException ex)
            {
                System.out.println(ex.getMessage());
            }
        }


        // ჩვეულებრივად
        try
        {
            rec.validate();
        }
        catch (ValidateException e)
        {
            System.out.println("Validation Finished Unsuccessfully");
            System.out.println(e.getMessage());
        }




    }
}





