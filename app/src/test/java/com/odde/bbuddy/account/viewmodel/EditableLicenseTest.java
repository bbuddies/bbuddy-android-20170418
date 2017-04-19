package com.odde.bbuddy.account.viewmodel;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.license.api.License;
import com.odde.bbuddy.license.api.LicenseApi;
import com.odde.bbuddy.license.view.AddLicenseCallbacks;
import com.odde.bbuddy.license.viewmodel.EditableLicense;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(NestedRunner.class)
public class EditableLicenseTest {

    LicenseApi mockLicenseApi = mock(LicenseApi.class);
    EditableLicense editableLicense = new EditableLicense(mockLicenseApi);
    AddLicenseCallbacks mockAddLicenseCallbacks = mock(AddLicenseCallbacks.class);

    public class Add {

        @Before
        public void init() {
            editableLicense.setCallbacks(mockAddLicenseCallbacks);
        }

        @Test
        public void add_should_correctly_add_account() {
            addLicense("2017-05", "100");
            verifyLicenseAddWithLicense(createLicense("2017-05", "100"));
        }

        @Test
        public void add_should_show_all_accounts_after_add_account_success() {
            addLicense("2017-05", "0");
            verify(mockLicenseApi, never()).addLicense(any(License.class));
        }
//
//        private void given_add_account_will_success() {
//            callRunnableAtIndex(1).when(mockLicenseApi).addLicense(any(Account.class), any(Runnable.class));
//        }

        private void verifyLicenseAddWithLicense(License license) {
            ArgumentCaptor<License> captor = forClass(License.class);
            verify(mockLicenseApi).addLicense(captor.capture());
            assertThat(captor.getValue()).isEqualToComparingFieldByField(license);
        }

        private void addLicense(String month, String amount) {
            editableLicense.setMonth(month);
            editableLicense.setAmount(amount);
            editableLicense.add();
        }
    }
//
//    public class Edit {
//
//        @Test
//        public void edit_should_correctly_edit_account() {
//            given_account_id_is(1);
//
//            editAccount("name", 100);
//
//            verifyAccountsEditWithAccount(account(1, "name", 100));
//        }
//
//        @Test
//        public void edit_should_show_all_accounts_after_success() {
//            given_account_id_is(1);
//            given_edit_account_will_success();
//
//            editAccount("name", 100);
//
//            verify(mockShowAllAccountsNavigation).navigate();
//        }
//
//        private void editAccount(String name, int balanceBroughtForward) {
//            editableLicense.setName(name);
//            editableLicense.setBalanceBroughtForward(balanceBroughtForward);
//            editableLicense.update();
//        }
//
//        private void verifyAccountsEditWithAccount(Account account) {
//            ArgumentCaptor<Account> captor = forClass(Account.class);
//            verify(mockLicenseApi).editAccount(captor.capture(), any(Runnable.class));
//            assertThat(captor.getValue()).isEqualToComparingFieldByField(account);
//        }
//
//        private void given_edit_account_will_success() {
//            callRunnableAtIndex(1).when(mockLicenseApi).editAccount(any(Account.class), any(Runnable.class));
//        }
//    }
//
//    public class Delete {
//
//        @Test
//        public void delete_should_correctly_delete_account() {
//            given_account_id_is(1);
//
//            editableLicense.delete();
//
//            verifyAccountsDeleteWithAccount(account(1));
//        }
//
//        @Test
//        public void delete_should_show_all_accounts_after_success() {
//            given_account_id_is(1);
//            given_account_delete_will_success();
//
//            editableLicense.delete();
//
//            verify(mockShowAllAccountsNavigation).navigate();
//        }
//
//        private void verifyAccountsDeleteWithAccount(Account account) {
//            ArgumentCaptor<Account> captor = forClass(Account.class);
//            verify(mockLicenseApi).deleteAccount(captor.capture(), any(Runnable.class));
//            assertThat(captor.getValue()).isEqualToComparingFieldByField(account);
//        }
//
//        private void given_account_delete_will_success() {
//            callRunnableAtIndex(1).when(mockLicenseApi).deleteAccount(any(Account.class), any(Runnable.class));
//        }
//
//    }
//
//    public class ShowBalance {
//
//        @Test
//        public void display_balance_brought_forward_for_view() {
//            editableLicense.setBalanceBroughtForward(100);
//
//            assertEquals("100", editableLicense.getBalanceBroughtForwardForView());
//        }
//
//        @Test
//        public void get_balance_brought_forward_from_view() {
//            editableLicense.setBalanceBroughtForwardForView("100");
//
//            assertEquals(100, editableLicense.getBalanceBroughtForward());
//        }
//
//        @Test
//        public void value_not_changed_if_balance_brought_forward_from_view_is_not_a_number() {
//            editableLicense.setBalanceBroughtForward(50);
//
//            editableLicense.setBalanceBroughtForwardForView("not a number");
//
//            assertEquals(50, editableLicense.getBalanceBroughtForward());
//        }
//
//    }
//
//    private void given_account_id_is(int id) {
//        editableLicense.setId(id);
//    }
//
    private License createLicense(String month, String amount) {
        License license = new License();
        license.setAmount(amount);
        license.setMonth(month);
        return license;
    }
//
//    private Account account(int id) {
//        Account account = new Account();
//        account.setId(id);
//        return account;
//    }
//
//    private Account account(int id, String name, int balanceBroughtForward) {
//        Account account = new Account();
//        account.setId(id);
//        account.setName(name);
//        account.setBalanceBroughtForward(balanceBroughtForward);
//        return account;
//    }
}
