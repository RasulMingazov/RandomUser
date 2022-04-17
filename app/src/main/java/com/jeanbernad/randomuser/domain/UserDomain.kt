package com.jeanbernad.randomuser.domain

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.presentation.UserPresentation

sealed class UserDomain: Abstract.Object<UserPresentation, Abstract.Mapper.Empty>  {
}