// package com.uca.hrm.comm;

// import org.springframework.beans.factory.InitializingBean;

// import com.uca.hrm.comm.exception.DomainInvalidException;

// import jakarta.validation.Validation;
// import jakarta.validation.Validator;

// public class Global {

//     // Set<ConstraintViolation<Test>> violations = validator.validate(test);
//     // violations.forEach(i -> System.out.println(i.getMessage()));
//     private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

//     public static Validator getValidator() {
//         return validator;
//     }

//     public static void validate(Object object) {

//         var violations = validator.validate(object);
//         if (!violations.isEmpty()) {
//             StringBuilder sb = new StringBuilder();
//             sb.append("\n");

//             int i = 1;
//             for (var v : violations) {
//                 String fieldName = v.getPropertyPath().toString();
//                 String message = v.getMessage();
//                 sb.append(i + ". " + fieldName + " -> " + message + "\n");
//                 i++;
//             }

//             throw new DomainInvalidException(sb.toString());
//         }

//         EmailVa
//     }
// }
